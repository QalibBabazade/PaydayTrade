package az.express.payday.util;

import az.express.payday.entity.Company;
import az.express.payday.enums.EnumAvailableStatus;
import az.express.payday.exception.ExceptionConstants;
import az.express.payday.exception.PaydayException;
import az.express.payday.repository.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class Utility {

    @Autowired
    private CompanyDao companyDao;


    /*************Check Token*****************/
    public void checkToken(Long userId,String token) throws PaydayException{

        if(userId == null || token == null){
            throw  new PaydayException(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data!");
        }
        Company company = companyDao.findCompanyByIdAndTokenAndActive(userId,token, EnumAvailableStatus.ACTIVE.getValue());
        if(company == null){
            throw  new PaydayException(ExceptionConstants.INVALID_TOKEN,"Invalid token");
        }

    }

    /*************Check Company*****************/
    public void checkCompany(String username,String password) throws Exception{
        if(username == null || password == null){
            throw  new PaydayException(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data!");
        }
        Company company = companyDao.findCompanyByUsernameAndPasswordAndActive(username,password,EnumAvailableStatus.ACTIVE.getValue());
        if(company == null){
            throw  new PaydayException(ExceptionConstants.USER_NOT_FOUND,"User not found!");
        }
    }

    /*************Register Company*****************/
    public void registerCompany(String username,String password,String email){
        if(username == null || password == null || email == null){
            throw new PaydayException(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data!");
        }
        if(username.length() < 3 || password.length() < 6 ){
            throw new PaydayException(ExceptionConstants.INVALID_NAME_OR_PASSWORD,"Invalid name or password!");
        }
        if(!(email.endsWith("@gmail.com"))){
            throw new PaydayException(ExceptionConstants.WRONG_EMAIL,"Wrong email!");
        }
        Company company = companyDao.findCompanyByUsernameAndEmailAndActive(username,email,EnumAvailableStatus.ACTIVE.getValue());
        if(company != null){
            throw new PaydayException(ExceptionConstants.AVAILABLE_USER,"Available user!");
        }
    }

    /******************Send Email******************/
   public void sendEmail(String email){

        JavaMailSender mailSender = new JavaMailSenderImpl() ;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setCc("qalibbabazade500@gmail.com");
        message.setText("Hello");
        message.setSubject("Confirmation link");
        mailSender.send(message);
    }
}
