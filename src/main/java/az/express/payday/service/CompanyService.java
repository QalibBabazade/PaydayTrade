package az.express.payday.service;

import az.express.payday.dto.request.ReqCompany;
import az.express.payday.dto.request.ReqSignIn;
import az.express.payday.dto.request.ReqToken;
import az.express.payday.dto.response.RespCompany;
import az.express.payday.dto.response.RespStatusObject;
import az.express.payday.dto.response.Response;

public interface CompanyService {
    Response<RespCompany> signIn(ReqSignIn reqSignIn);

    RespStatusObject logout(ReqToken reqToken);

    RespStatusObject signUp(ReqCompany reqCompany);
}
