package br.com.dopad.web.controller;

import br.com.dopad.domain.entities.page.Page;
import br.com.dopad.usecases.page.PageCRUD;
import br.com.dopad.web.model.page.request.PageRequest;
import br.com.dopad.web.model.page.response.PageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/pages")
public class PageController {

    private final PageCRUD pageCRUD;

    public PageController(PageCRUD pageCRUD) {
        this.pageCRUD = pageCRUD;
    }

    @PostMapping("/add")
    public ResponseEntity<PageResponse> addPage(@RequestBody PageRequest request) {
        Page page = pageCRUD.addPage(request);
        return ResponseEntity.ok(PageResponse.fromPage(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PageResponse> getPageById(@PathVariable UUID id) {
        Page page = pageCRUD.getPageById(id);
        return ResponseEntity.ok(PageResponse.fromPage(page));
    }

}
