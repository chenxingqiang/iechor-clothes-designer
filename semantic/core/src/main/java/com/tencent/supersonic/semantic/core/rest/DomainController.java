package com.tencent.supersonic.semantic.core.rest;

import com.tencent.supersonic.auth.api.authentication.pojo.User;
import com.tencent.supersonic.auth.api.authentication.utils.UserHolder;
import com.tencent.supersonic.semantic.api.core.request.DomainReq;
import com.tencent.supersonic.semantic.api.core.request.DomainSchemaFilterReq;
import com.tencent.supersonic.semantic.api.core.request.DomainUpdateReq;
import com.tencent.supersonic.semantic.api.core.response.DomainResp;
import com.tencent.supersonic.semantic.api.core.response.DomainSchemaResp;
import com.tencent.supersonic.semantic.core.domain.DomainService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/semantic/domain")
public class DomainController {

    private DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @PostMapping("/createDomain")
    public Boolean createDomain(@RequestBody DomainReq domainReq,
            HttpServletRequest request,
            HttpServletResponse response) {
        User user = UserHolder.findUser(request, response);
        domainService.createDomain(domainReq, user);
        return true;
    }

    @PostMapping("/updateDomain")
    public Boolean updateDomain(@RequestBody DomainUpdateReq domainUpdateReq,
            HttpServletRequest request,
            HttpServletResponse response) {
        User user = UserHolder.findUser(request, response);
        domainService.updateDomain(domainUpdateReq, user);
        return true;
    }

    @DeleteMapping("/deleteDomain/{domainId}")
    public Boolean deleteDomain(@PathVariable("domainId") Long domainId) {
        domainService.deleteDomain(domainId);
        return true;
    }


    /**
     * get domain list
     *
     * @param
     */
    @GetMapping("/getDomainList")
    public List<DomainResp> getDomainList(HttpServletRequest request,
            HttpServletResponse response) {
        User user = UserHolder.findUser(request, response);
        return domainService.getDomainListForAdmin(user.getName());
    }


    /**
     * get domain by id
     *
     * @param id
     * @return
     */
    @GetMapping("/getDomain/{id}")
    public DomainResp getDomain(@PathVariable("id") Long id) {
        return domainService.getDomain(id);
    }

    @GetMapping("/getDomainListByIds/{domainIds}")
    public List<DomainResp> getDomainListByIds(@PathVariable("domainIds") String domainIds) {
        return domainService.getDomainList(Arrays.stream(domainIds.split(",")).map(Long::parseLong)
                .collect(Collectors.toList()));
    }

    @PostMapping("/schema")
    public List<DomainSchemaResp> fetchDomainSchema(@RequestBody DomainSchemaFilterReq filter,
            HttpServletRequest request,
            HttpServletResponse response) {
        User user = UserHolder.findUser(request, response);
        return domainService.fetchDomainSchema(filter, user);
    }


}
