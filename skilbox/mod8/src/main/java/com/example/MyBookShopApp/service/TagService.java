package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.tags.Tag;
import com.example.MyBookShopApp.repository.Book2TagRepository;
import com.example.MyBookShopApp.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private Book2TagRepository book2TagRepository;

    public Tag getTagBySlug(String slug) {
        return tagRepository.findBySlug(slug);
    }

    public Map<Tag, String> getTagCloud() {
        var tags = tagRepository.findAll();
        var cloud = new HashMap<Tag, String>();
        var tagCount = new HashMap<Tag, Long>();
        var size = List.of("Tag", "Tag_xs", "Tag_sm", "Tag_md", "Tag_lg");
        var max = -1L;
        var min = 1000L;

        for (var tag : tags) {
            var count = book2TagRepository.countByTag(tag);
            max = Math.max(max, count);
            min = Math.min(min, count);
            tagCount.put(tag, count);
        }
        max -= min;
        for (var tag : tagCount.keySet()) {
            var tp = tagCount.get(tag) - min;
            tagCount.put(tag, tp);
            var p = ((double) tp) / ((double) max);
            var idSize = (int) (p * (size.size() - 1));
            cloud.put(tag, size.get(idSize));
        }
        min = 0;
        return cloud;
    }

}
