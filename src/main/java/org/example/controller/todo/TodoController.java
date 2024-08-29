package org.example.controller.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Todo;
import org.example.repository.todo.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/todo")
@CrossOrigin(value = "*")
public class TodoController {

    private final TodoRepository todoRepository;

    @GetMapping("")
    public ResponseEntity<List<Todo>> findAll() {
        List<Todo> todoList = todoRepository.findAll();
        return ResponseEntity.ok(todoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> findById(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id);
        return ResponseEntity.ok(todo);
    }

    @PostMapping("/{todo}")
    private ResponseEntity<Todo> save(@PathVariable String todo) {
        Todo addTodo = todoRepository.save(new Todo(todo));
        return ResponseEntity.ok(addTodo);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> editDone(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id);
        if (todo == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 id값을 찾을 수 없습니다");
        boolean done = todo.isDone();
        done  = !done;
        todoRepository.editDone(id,done);
        return ResponseEntity.ok(todo);
    }

    @PutMapping(value = "/update/{id}/{todo}")
    public ResponseEntity<?> editContent(@PathVariable Long id, @PathVariable("todo") String content) {
        Todo todo = todoRepository.findById(id);
        if (todo == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 id값을 찾을 수 없습니다");

        todoRepository.editContent(id, content);
        return ResponseEntity.ok(todoRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Todo findTodo = todoRepository.findById(id);
        if (findTodo==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("삭제 실패");
        todoRepository.delete(id);
        return ResponseEntity.accepted().body("삭제 완료");
    }
}
