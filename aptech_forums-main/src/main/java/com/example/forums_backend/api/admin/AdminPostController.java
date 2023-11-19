package com.example.forums_backend.api.admin;

import com.example.forums_backend.dto.PostRequestDto;
import com.example.forums_backend.dto.PostResDto;
import com.example.forums_backend.exception.AppException;
import com.example.forums_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.forums_backend.config.constant.route.AdminRoute.*;

@RestController
@RequestMapping(PREFIX_ADMIN_ROUTE)
@RequiredArgsConstructor
public class AdminPostController {
    @Autowired
    PostService postService;
//    @RequestMapping(value = POST_PATH, method = RequestMethod.GET)
//    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "none") SortPost sort){
//        return ResponseEntity.ok(repository.findAll());
//    }

    @RequestMapping(value = POST_PATH, method = RequestMethod.POST)
    public ResponseEntity<?> createPost(@RequestBody PostRequestDto postRequestDto) {
        return ResponseEntity.ok(postService.savePost(postRequestDto));
    }

    @RequestMapping(value = POST_PATH_WITH_ID, method = RequestMethod.PUT)
    public ResponseEntity<PostResDto> updatePostByAdmin(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) throws AppException {
        // Gọi service để thực hiện chức năng cập nhật bài đăng bởi admin
        PostResDto updatedPost = postService.updatePostByAdmin(id, postRequestDto);
        return ResponseEntity.ok(updatedPost);
    }

    @RequestMapping(value = POST_PATH_WITH_ID, method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.ok("Deleted");
    }

//    @RequestMapping(value = POST_PATH, method = RequestMethod.GET)
//    public Page<Post> findPage(@RequestParam int page, @RequestParam int size) {
//        PageRequest pageRequest = PageRequest.of(page, size);
//        return repository.findAll(pageRequest);
//    }
}