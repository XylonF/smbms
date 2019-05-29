package cn.smbms.services.provider;

import cn.smbms.pojo.Provider;

import java.util.List;

public interface ProviderServices {
    public List<Provider> findProviders(String proName);

    public boolean addProvider (Provider provider);

    public boolean updateProvider(Provider cProvider);

    public boolean deleteProvider(int id);
}
