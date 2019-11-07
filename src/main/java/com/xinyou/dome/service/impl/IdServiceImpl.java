package com.xinyou.dome.service.impl;

import com.xinyou.dome.dao.SequenceDao;
import com.xinyou.dome.entity.SequenceEntity;
import com.xinyou.dome.service.IdService;
import com.xinyou.dome.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/5/6 15:25
 * @Description:
 */
@Service
public class IdServiceImpl implements IdService {

    @Autowired
    private SequenceDao sequenceDao;
    long stepId = 0;
    public static String time = "";
    long getId = 0;

    @Transactional
    @Override
    public synchronized String getId() {
        if (stepId == 0) {
            List<SequenceEntity> list = sequenceDao.query(1);
            if (list.size() > 0) {
                SequenceEntity entity = list.get(0);
                if (entity.getMaxValue() > 0) {
                    sequenceDao.update(entity.id);
                    time = DataUtil.formatDate(new Date(), DataUtil.DATE_PATTEN_DAY_M);
                    stepId = entity.step;
                }
            }
        }
        getId++;
        stepId--;
        switch (String.valueOf(getId).length()) {
            case 1:
                return time + "000" + getId;
            case 2:
                return time + "00" + getId;
            case 3:
                return time + "0" + getId;
            case 4:
                String id = time + getId;
                getId = 0;
                return id;
            default:
                return time + getId;
        }

    }
}
