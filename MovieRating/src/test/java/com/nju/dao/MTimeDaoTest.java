package com.nju.dao;

import com.nju.IntegrationApplication;
import com.nju.entity.MTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegrationApplication.class)
public class MTimeDaoTest {

    @Autowired
    MTimeDao mTimeDao;

    @Test
    public void name() throws Exception {
        MTime mTime = new MTime();
        mTime.setMovieId(1);
        mTime.setName("bcy");
        mTime.setRate(10);
        mTimeDao.save(mTime);
    }
}
