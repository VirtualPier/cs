package org.ligson.coderstar2.orgz.service.impl;

import org.ligson.coderstar2.orgz.dao.OrgDetailDao;
import org.ligson.coderstar2.orgz.domains.OrgDetail;
import org.ligson.coderstar2.orgz.service.OrgzService;

import java.util.List;

/**
 * Created by ligson on 2015/11/9.
 */
public class OrgzServiceImpl implements OrgzService {
    private OrgDetailDao orgDetailDao;

    @Override
    public void regOrg(OrgDetail orgDetail) {
        orgDetail.setStatus(1);
        orgDetailDao.saveOrUpdate(orgDetail);
    }

    @Override
    public void auditOrg(OrgDetail orgDetail) {
        orgDetail.setStatus(2);
        orgDetailDao.saveOrUpdate(orgDetail);
    }

    @Override
    public List<OrgDetail> query(OrgDetail orgDetail, int max, int offset) {
        return orgDetailDao.list(offset, max);
    }

    @Override
    public long count(OrgDetail orgDetail) {
        return orgDetailDao.countByExample(orgDetail);
    }
}
