package com.cinemastore.privateservice.criteria;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<REQ, ENTITY> {
    Specification<ENTITY> getSpecification(REQ req);
}
