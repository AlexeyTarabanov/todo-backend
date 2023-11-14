package com.example.todobackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Convert;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.MapsId;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

/**
 * Вся активность пользователя (активация аккаунта, другие действия по необходимости)
 * */

@Entity // Обозначает класс как сущность, с которой будет взаимодействовать Hibernate
@Table(name = "activity", schema = "todolist", catalog = "postgres")
@NoArgsConstructor // Генерируют конструкторы без аргументов и с аргументами соответственно
@AllArgsConstructor
@Setter
@Getter
@Cacheable // Обозначает, что экземпляры класса могут быть кешированы
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Activity { // название таблицы будет браться автоматически по названию класса с маленькой буквы: activity

    @Id // поле, которое является первичным ключом
    @GeneratedValue(strategy = GenerationType.IDENTITY) // значения первичных ключей будут автоматически генерироваться БД
    private Long id;

    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class) // для автоматической конвертации числа в true/false
    private Boolean activated; // становится true только после подтверждения активации пользователем (обратно false уже стать не может по логике)

    @Column(updatable = false)
    private String uuid; // создается только один раз с помощью триггера в БД

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // говорит Hibernate использовать идентификатор другой сущности в качестве идентификатора текущей сущности (Activity)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return id.equals(activity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}