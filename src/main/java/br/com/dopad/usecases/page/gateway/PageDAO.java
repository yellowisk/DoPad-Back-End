package br.com.dopad.usecases.page.gateway;

import br.com.dopad.domain.entities.page.Page;

public interface PageDAO {
    Page savePage(Page page);
}
