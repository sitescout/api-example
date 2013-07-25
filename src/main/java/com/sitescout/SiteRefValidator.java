package com.sitescout;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sitescout.dsp.api.model.dto.ErrorDTO;
import com.sitescout.ui.api.APIConnection;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;


/**
 * Validates the user input for site reference.
 */
@FacesValidator("com.sitescout.SiteRefValidator")
public class SiteRefValidator implements Validator {

    JsonParser result;

    @Override
    public void validate(FacesContext context, UIComponent component,
                         Object value) throws ValidatorException {
        String url = "https://api.sitescout.com/sites/" + value + "/deniedAttributes";
        String pattern = "\\d{1,2}-\\d+";
        if (!value.toString().matches(pattern)) {
            FacesMessage msg = new FacesMessage("SiteRefs are #-#####... or ##-#####...");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        try {
            result = getApiConnection().getData(url);
        } catch (IOException e) {
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            FacesMessage msg;
            ErrorDTO error = mapper.readValue(result, ErrorDTO.class);
            String errorCode = error.getErrorCode();
            if (errorCode.equals("SE00004")) {
                msg = new FacesMessage("SiteRef does not exist.");
            } else {
                msg =  new FacesMessage("You have no access to that SiteRef");
            }
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        } catch (IOException e) {

        }
    }

    public BeanManager getBeanManager()
    {
        try{
            InitialContext initialContext = new InitialContext();
            return (BeanManager) initialContext.lookup("java:comp/BeanManager");
        }
        catch (NamingException e) {
            return null;
        }
    }

    public APIConnection getApiConnection()
    {
        BeanManager bm = getBeanManager();
        Bean<APIConnection> bean = (Bean<APIConnection>) bm.getBeans(APIConnection.class).iterator().next();
        CreationalContext<APIConnection> ctx = bm.createCreationalContext(bean);
        APIConnection apiConnection = (APIConnection) bm.getReference(bean, APIConnection.class, ctx);
        return apiConnection;
    }
}

