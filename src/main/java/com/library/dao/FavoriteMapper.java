package com.library.dao;

import com.library.entity.Favorite;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface FavoriteMapper {
    int addFavorite(@Param("userId") Integer userId, @Param("bookId") Integer bookId);
    int removeFavorite(@Param("userId") Integer userId, @Param("bookId") Integer bookId);
    List<Favorite> getFavoritesByUserId(@Param("userId") Integer userId);
    Favorite getFavorite(@Param("userId") Integer userId, @Param("bookId") Integer bookId);
} 