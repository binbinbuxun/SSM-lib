package com.library.controller;

import com.library.entity.Favorite;
import com.library.service.FavoriteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/add")
    public Map<String, Object> addFavorite(@RequestBody Map<String, Integer> payload, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        Integer userId = ((com.library.entity.User) userObj).getId();
        Integer bookId = payload.get("bookId");
        boolean success = favoriteService.addFavorite(userId, bookId);
        result.put("success", success);
        result.put("msg", success ? "收藏成功" : "收藏失败");
        return result;
    }

    @PostMapping("/remove")
    public Map<String, Object> removeFavorite(@RequestBody Map<String, Integer> payload, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        Integer userId = ((com.library.entity.User) userObj).getId();
        Integer bookId = payload.get("bookId");
        boolean success = favoriteService.removeFavorite(userId, bookId);
        result.put("success", success);
        result.put("msg", success ? "取消收藏成功" : "取消收藏失败");
        return result;
    }

    @GetMapping("/list")
    public Map<String, Object> getFavorites(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        Integer userId = ((com.library.entity.User) userObj).getId();
        List<Favorite> favorites = favoriteService.getFavoritesByUserId(userId);
        result.put("success", true);
        result.put("data", favorites);
        return result;
    }

    @GetMapping("/isFavorite")
    public Map<String, Object> isFavorite(@RequestParam Integer bookId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        Integer userId = ((com.library.entity.User) userObj).getId();
        boolean isFav = favoriteService.isFavorite(userId, bookId);
        result.put("success", true);
        result.put("isFavorite", isFav);
        return result;
    }
} 