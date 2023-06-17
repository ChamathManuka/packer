package com.travel.backpacker.operations;

import com.travel.backpacker.controllers.endpointcontroller.search.controllers.HolidayController;
import com.travel.backpacker.dao.AdminDao;
import com.travel.backpacker.dao.OperatorDao;
import com.travel.backpacker.dao.PassengerDao;
import com.travel.backpacker.dao.schedulers.content.HotelDao;
import com.travel.backpacker.models.UserLoginAction;
import com.travel.backpacker.models.search.GlobalSearch;
import com.travel.backpacker.properties.DefaultPackerProperties;
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

    public UserLoginAction getUserLoginAction() {
        return factory.getBean(UserLoginAction.class);
    }

    public HotelDao getHotelDao() {
        return factory.getBean(HotelDao.class);
    }

    public HolidayController getHolidayController() {
        return factory.getBean(HolidayController.class);
    }

    public GlobalSearch getGlobalSearch() {
        return factory.getBean(GlobalSearch.class);
    }

}
