package com.library.service;

import com.library.entity.Favorite;
import java.util.List;

public interface FavoriteService {
    boolean addFavorite(Integer userId, Integer bookId);
    boolean removeFavorite(Integer userId, Integer bookId);
    List<Favorite> getFavoritesByUserId(Integer userId);
    boolean isFavorite(Integer userId, Integer bookId);
} 