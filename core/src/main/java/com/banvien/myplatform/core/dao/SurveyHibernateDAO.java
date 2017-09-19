package com.banvien.myplatform.core.dao;

import com.banvien.myplatform.core.Constants;
import com.banvien.myplatform.core.domain.Survey;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SurveyHibernateDAO extends
        AbstractHibernateDAO<Survey, Integer> implements
        SurveyDAO {

    @Override
    public Object[] search(final Byte status,
                           final Date createdDateFrom, final Date createdDateTo,
                           final Integer firstItem, final Integer maxItems, final String sortExpression,
                           final String sortDirection) {

        Object[] res;

        try{
            StringBuffer whereClause = new StringBuffer(" 1 = 1 ");

            if (createdDateFrom != null) {
                whereClause.append(" AND s.createdDate >= :createdDateFrom");
            }
            if (createdDateTo != null) {
                whereClause.append(" AND s.createdDate <= :createdDateTo");
            }
            if(status != null) {
                whereClause.append(" AND s.status = :status");
            }

            final String where = whereClause.toString();
            List<Survey> items = getHibernateTemplate().execute(
                    new HibernateCallback<List<Survey>>() {
                        public List<Survey> doInHibernate(Session session)
                                throws HibernateException, SQLException {
                            StringBuffer listSQL = new StringBuffer();
                            listSQL.append("SELECT s FROM Survey s WHERE ");
                            listSQL.append(where);
                            if (StringUtils.isNotBlank(sortExpression)) {
                                listSQL.append(" ORDER BY s.").append(
                                        sortExpression);
                                if (StringUtils.isNotBlank(sortDirection)
                                        && Constants.SORT_ASC.equals(sortDirection)) {
                                    listSQL.append(" ASC");
                                } else {
                                    listSQL.append(" DESC");
                                }
                            }else {
                                listSQL.append(" ORDER BY s.createdDate DESC");
                            }
                            Query query = session
                                    .createQuery(listSQL.toString());
                            if (createdDateFrom != null) {
                                query.setParameter("createdDateFrom", createdDateFrom);
                            }
                            if (createdDateTo != null) {
                                query.setParameter("createdDateTo", createdDateTo);
                            }
                            if(status != null) {
                                query.setParameter("status", status);
                            }

                            if(firstItem != null) {
                                query.setFirstResult(firstItem);
                            }
                            if(maxItems != null) {
                                query.setMaxResults(maxItems);
                            }
                            return (List<Survey>) query.list();
                        }
                    });

            Long total = getHibernateTemplate().execute(
                    new HibernateCallback<Long>() {
                        public Long doInHibernate(Session session)
                                throws HibernateException, SQLException {
                            StringBuffer countSQL = new StringBuffer();
                            countSQL.append(" SELECT COUNT(*) FROM Survey s WHERE");
                            countSQL.append(where);
                            Query query = session.createQuery(countSQL.toString());

                            if (createdDateFrom != null) {
                                query.setParameter("createdDateFrom", createdDateFrom);
                            }
                            if (createdDateTo != null) {
                                query.setParameter("createdDateTo", createdDateTo);
                            }
                            if(status != null) {
                                query.setParameter("status", status);
                            }

                            return (Long) query.uniqueResult();
                        }
                    });
            res = new Object[]{total, items};
        }catch (Exception e){
            logger.error(e);
            res = new Object[]{0, new ArrayList<Survey>()};
        }
        return res;
    }

}
