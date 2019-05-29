package cn.smbms.dao.provider;

import cn.smbms.pojo.Provider;

import java.util.List;

public interface ProviderDao {
    /**
     * 根据供应商名模糊查询供应商
     * */
    public List<Provider> queryProvider(Provider cProvider);

    public int addProvider(Provider provider);

    public int updateProvider(Provider cProvider);

    public int deleteProvider(int id);
}
