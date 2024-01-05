package br.com.dopad.usecases.page.gateway;

import br.com.dopad.domain.entities.page.Page;

import java.util.*;

public interface PageDAO {
    Page savePage(Page page);
    Optional<Page> getPageById(UUID id);
}
