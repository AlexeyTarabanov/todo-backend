package com.example.todobackend.repo;

import com.example.todobackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// принцип ООП: абстракция-реализация - здесь описываем все доступные способы доступа
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
