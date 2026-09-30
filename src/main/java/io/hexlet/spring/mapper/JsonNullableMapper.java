package io.hexlet.spring.mapper;

import org.mapstruct.Mapper;
import org.openapitools.jackson.nullable.JsonNullable;

/**
 * Помогает MapStruct'у понимать JsonNullable<T>:
 *  - unwrap: JsonNullable<T> -> T
 *  - wrap:   T -> JsonNullable<T>
 */
@Mapper(componentModel = "spring")
public interface JsonNullableMapper {

    default <T> JsonNullable<T> wrap(T entity) {
        return JsonNullable.of(entity);
    }

    default <T> T unwrap(JsonNullable<T> nullable) {
        return nullable == null ? null : nullable.orElse(null);
    }
}