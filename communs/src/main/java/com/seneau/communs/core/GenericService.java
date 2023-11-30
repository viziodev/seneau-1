package com.seneau.communs.core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seneau.communs.data.dto.PageListMapper;
import com.seneau.communs.web.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public abstract class GenericService<T extends GenericEntity<T>, D, R> {
    /*
    * D : ResponseDto
    * R : ResquestDto
    * T : entity
    *  */
    private PageListMapper pageListMapper = new PageListMapper();
    private static final ThreadLocal<ObjectMapper> om = new ThreadLocal<ObjectMapper>() {
        @Override
        protected ObjectMapper initialValue() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper;
        }
    };

    public static ObjectMapper getObjectMapper() {
        return om.get();
    }
    private final GenericRepository<T> repository;

    protected Class<T> TClass;
    protected Class<D> DClass;

    public GenericService(GenericRepository<T> repository, Class<T> TClass, Class<D> DClass) {
        this.repository = repository;
        this.TClass = TClass;
        this.DClass = DClass;
    }

    public Map<String, Object> getPage(int page, int pageSize){
        Pageable paging = PageRequest.of(page, pageSize);
        Page<T> tPage = repository.findAllByActiveTrue(paging);
        List<D> content = tPage.getContent().stream().map(this::transformEntityToResponseDTO).toList();
        return pageListMapper.getPageToMapObject(
                content,
                tPage.getNumber(),
                tPage.getTotalElements(),
                tPage.getTotalPages()
        );
    }

    public T get(Long id){
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(id.toString())
        );
    }

    @Transactional
    public T update(T updated){
        T dbDomain = get(updated.getId());
        dbDomain.update(updated);

        return repository.save(dbDomain);
    }

    @Transactional
    public T create(T newDomain){
        T dbDomain = newDomain.createNewInstance();
        return repository.save(dbDomain);
    }

    @Transactional
    public void delete(Long id){
        //check if object with this id exists
        get(id);
        repository.deleteById(id);
        //repository.updateEntity(id);
    }

    public T transformRequestDTOToEntity(R element) {
        return getObjectMapper().convertValue(element, TClass);
    }
    public D transformEntityToResponseDTO(T element) {
        return getObjectMapper().convertValue(element, DClass);
    }
}
