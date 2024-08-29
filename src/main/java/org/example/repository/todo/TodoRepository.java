package org.example.repository.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Todo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TodoRepository {
    private final EntityManager em;

    public List<Todo> findAll(){
        String jpql = "select b from Todo b";
        List<Todo> todoList = em.createQuery(jpql, Todo.class).getResultList();
        return todoList;
    }

    public Todo findById(Long id){
        return em.find(Todo.class, id);
    }

    public Todo save(Todo todo){
        em.persist(todo);
        return todo;
    }

    public void editDone(Long id,boolean done){
        Todo todo = em.find(Todo.class, id);
        Todo doneTodo = new Todo(todo.getId(), todo.getTodo(), done);
        em.merge(doneTodo);
    }

    public void editContent(Long id, String content){
        Todo todo = em.find(Todo.class, id);
        Todo editTodo = new Todo(todo.getId(), content, todo.isDone());
        em.merge(editTodo);
    }

    public void delete(Long id){
        Todo todo = findById(id);
        if (todo != null) {
            em.remove(todo);
        }
    }
}
