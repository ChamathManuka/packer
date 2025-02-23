package com.travel.backpacker.service.operations;

import com.travel.backpacker.repository.AdminDao;
import com.travel.backpacker.repository.OperatorDao;
import com.travel.backpacker.repository.PassengerDao;

import com.travel.backpacker.dto.UserLoginAction;
import com.travel.backpacker.properties.DefaultPackerProperties;
import com.travel.backpacker.repository.accommodation.*;
import com.travel.backpacker.service.mfa.MFAEmailService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class OperationRequiredComponents implements BeanFactoryAware {
    private BeanFactory factory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.factory = beanFactory;
    }

    public DefaultPackerProperties getPackerProperties() {
        return factory.getBean(DefaultPackerProperties.class);
    }

    public PassengerDao getPassengerDao() {
        return factory.getBean(PassengerDao.class);
    }

    public AdminDao getAdminDao() {
        return factory.getBean(AdminDao.class);
    }

    public OperatorDao getOperatorDao() {
        return factory.getBean(OperatorDao.class);
    }

    public SCryptPasswordEncoder getPasswordEncorder() {
        return (SCryptPasswordEncoder) factory.getBean("createPasswordEncorder");
    }
    public MFAEmailService getMFAEmailService() {return factory.getBean(MFAEmailService.class);}
    public UserLoginAction getUserLoginAction() {
        return factory.getBean(UserLoginAction.class);
    }

    public HotelDao getHotelDao() {
        return factory.getBean(HotelDao.class);
    }

    public RoomDao getRoomDao() { return factory.getBean(RoomDao.class); }

    public AvailabilityDao getAvailabilityDao() {return factory.getBean(AvailabilityDao.class); }

    public RoomTypeDao getRoomTypeDao() {return factory.getBean(RoomTypeDao.class); }

    public RoomImageDao getRoomImageDao() {return factory.getBean(RoomImageDao.class); }

    public HotelImageDao getHotelImageDao() {return factory.getBean(HotelImageDao.class); }


}
