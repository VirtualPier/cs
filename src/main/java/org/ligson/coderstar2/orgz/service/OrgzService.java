package org.ligson.coderstar2.orgz.service;

import org.ligson.coderstar2.orgz.domains.OrgDetail;

import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface OrgzService {
    public void regOrg(OrgDetail orgDetail);

    public void auditOrg(OrgDetail orgDetail);

    public List<OrgDetail> query(OrgDetail orgDetail, int max, int offset);

    public List<OrgDetail> count(OrgDetail orgDetail);
}
