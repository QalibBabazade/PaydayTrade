package az.express.payday.controller;

import az.express.payday.dto.request.ReqCompany;
import az.express.payday.dto.request.ReqSignIn;
import az.express.payday.dto.request.ReqToken;
import az.express.payday.dto.response.RespCompany;
import az.express.payday.dto.response.RespStatusObject;
import az.express.payday.dto.response.Response;
import az.express.payday.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping(value = "/signin")
    public Response<RespCompany> signIn(@RequestBody ReqSignIn reqSignIn){
        return companyService.signIn(reqSignIn);
    }

    @PostMapping(value = "/logout")
    public RespStatusObject logout(@RequestBody ReqToken reqToken){
        return companyService.logout(reqToken);
    }

    @PostMapping(value = "/signup")
    public RespStatusObject signUp(@RequestBody ReqCompany reqCompany) {
        return companyService.signUp(reqCompany);

    }
}
