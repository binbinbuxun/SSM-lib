package com.library.service.impl;

import com.library.dao.FavoriteMapper;
import com.library.entity.Favorite;
import com.library.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    public boolean addFavorite(Integer userId, Integer bookId) {
        return favoriteMapper.addFavorite(userId, bookId) > 0;
    }

    @Override
    public boolean removeFavorite(Integer userId, Integer bookId) {
        return favoriteMapper.removeFavorite(userId, bookId) > 0;
    }

    @Override
    public List<Favorite> getFavoritesByUserId(Integer userId) {
        return favoriteMapper.getFavoritesByUserId(userId);
    }

    @Override
    public boolean isFavorite(Integer userId, Integer bookId) {
        return favoriteMapper.getFavorite(userId, bookId) != null;
    }
} 