package cn.smbms.services.provider;

import cn.smbms.dao.provider.ProviderDao;
import cn.smbms.pojo.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("providerServices")
public class ProviderServicesImpl implements ProviderServices {
    @Autowired
    @Qualifier("providerDao")
    ProviderDao providerDao;

    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    @Override
    public List<Provider> findProviders(String proName) {
        List<Provider> providers = null;
        Provider cProvider = new Provider();
        cProvider.setProName(proName);
        providers = providerDao.queryProvider(cProvider);
        return providers;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean addProvider(Provider provider) {
        int b=0;
        provider.setCreationDate(new Date());
        b = providerDao.addProvider(provider);
        if (b==1){
            return true;
        }
        return false;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateProvider(Provider cProvider) {
        cProvider.setModifyDate(new Date());
        int i = providerDao.updateProvider(cProvider);
        if (i==1){
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteProvider(int id) {
        int i = providerDao.deleteProvider(id);
        if (i==1){
            return true;
        }
        return false;
    }
}
