package br.com.dopad.usecases.page;

import br.com.dopad.domain.entities.page.Page;
import br.com.dopad.web.model.page.request.PageRequest;

import java.util.*;

public interface PageCRUD {
    Page addPage(PageRequest request);
    Page getPageById(UUID id);
}
