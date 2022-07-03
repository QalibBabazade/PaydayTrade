package az.express.payday.service.impl;

import az.express.payday.dto.request.ReqCompany;
import az.express.payday.dto.request.ReqSignIn;
import az.express.payday.dto.request.ReqToken;
import az.express.payday.dto.response.RespCompany;
import az.express.payday.dto.response.RespStatus;
import az.express.payday.dto.response.RespStatusObject;
import az.express.payday.dto.response.Response;
import az.express.payday.entity.Company;
import az.express.payday.enums.EnumAvailableStatus;
import az.express.payday.exception.ExceptionConstants;
import az.express.payday.exception.PaydayException;
import az.express.payday.repository.CompanyDao;
import az.express.payday.service.CompanyService;
import az.express.payday.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private Utility utility;


    @Override
    public Response<RespCompany> signIn(ReqSignIn reqSignIn) {
        Response<RespCompany> response = new Response<>();
        RespCompany respCompany = new RespCompany();
        try {
            String username = reqSignIn.getUsername();
            String password = reqSignIn.getPassword();
            utility.checkCompany(username, password);
            Company company = companyDao.findCompanyByUsernameAndPasswordAndActive(username, password, EnumAvailableStatus.ACTIVE.getValue());
            if (company.getToken() != null) {
                throw new PaydayException(ExceptionConstants.USER_IS_ALREADY_IN_SESSION, "User is already in session!");
            }

            String token = UUID.randomUUID().toString();
            company.setToken(token);
            companyDao.save(company);
            respCompany.setUserId(company.getId());
            respCompany.setUsername(company.getUsername());
            respCompany.setEmail(company.getEmail());
            respCompany.setBalance(company.getBalance());
            respCompany.setToken(company.getToken());
            response.setT(respCompany);
            response.setRespStatus(RespStatus.getSuccessMessage());
        } catch (PaydayException ex) {
            response.setRespStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();

        } catch (Exception ex) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception!"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public RespStatusObject logout(ReqToken reqToken) {
        RespStatusObject response = new RespStatusObject();
        try {
            Long userId = reqToken.getUserId();
            String token = reqToken.getToken();
            utility.checkToken(userId, token);
            Company company = companyDao.findCompanyByIdAndTokenAndActive(userId, token, EnumAvailableStatus.ACTIVE.getValue());
            company.setToken(null);
            companyDao.save(company);
            response.setRespStatus(RespStatus.getSuccessMessage());

        } catch (PaydayException ex) {
            response.setRespStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();

        } catch (Exception ex) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception!"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public RespStatusObject signUp(ReqCompany reqCompany) {
        RespStatusObject response = new RespStatusObject();
        try {
            String username = reqCompany.getUsername();
            String password = reqCompany.getPassword();
            String email = reqCompany.getEmail();
            utility.registerCompany(username, password, email);
            Company company = new Company();
            company.setUsername(username);
            company.setPassword(password);
            company.setEmail(email);
            companyDao.save(company);
            response.setRespStatus(RespStatus.getSuccessMessage());
        } catch (PaydayException ex) {
            response.setRespStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();

        } catch (Exception ex) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception!"));
            ex.printStackTrace();
        }
        return response;
    }
}
