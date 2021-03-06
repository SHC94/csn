package com.csn.csn.main.service;

import com.csn.csn.Item.entity.Item;
import com.csn.csn.Item.entity.NewsItem;
import com.csn.csn.comm.NaverApiCall;
import com.csn.csn.comm.NaverApiConstants;
import com.csn.csn.main.entity.Tab;
import com.csn.csn.main.repository.MainRepository;
import com.csn.csn.main.vo.LoginForm;
import com.csn.csn.main.vo.SearchParam;
import com.csn.csn.member.entity.Member;
import com.csn.csn.search.entity.Search;
import com.csn.csn.session.service.SessionService;
import com.csn.csn.session.vo.SessionRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainServiceImpl implements MainService {

    private final MainRepository mainRepository;
    private final SessionService sessionService;

    private final NaverApiCall naverApiCall;

    @Value("${api.naver.client_id}")
    private String clientId;

    @Value("${api.naver.client_secret}")
    private String clientSecret;

    /**
     * 탭 메뉴 조회
     * @return
     */
    @Override
    public List<Tab> selectTab() {
        return mainRepository.selectTab();
    }//end selectTab()

    /**
     * 가입 회원 조회
     * @param   loginForm
     * @return  boolean
     */
    @Override
    public boolean membershipFind(LoginForm loginForm, HttpSession session) {
        List<Member> member = mainRepository.membershipFind(loginForm);
        boolean result      = false;

        //세선 정보 저장
        if(!CollectionUtils.isEmpty(member)) {
            SessionRequestVo sessionRequestVo = new SessionRequestVo();
            sessionRequestVo.setId(loginForm.getLoginId());             //아이디
            sessionRequestVo.setName(member.get(0).getName());          //이름
            sessionRequestVo.setEmail(member.get(0).getEmail());        //이메일
            sessionRequestVo.setLoginWay("Normal");                     //로그인 수단

            sessionService.connectionSession(session, sessionRequestVo);
            result = true;
        }//end if()

        return result;
    }//end membershipFind()

    /**
     * 최근 검색어 조회
     * @param searchParam
     * @return
     */
    @Override
    public List<Search> selectSearchList(SearchParam searchParam) {
        return mainRepository.selectSearchList(searchParam);
    }//end selectSearchList()

    /**
     * 뉴스 정보 조회 (다건)
     * @return
     */
    @Override
    public List<NewsItem> selectNewsList() {
        List<NewsItem> resultList = new ArrayList<>();

        try{

            resultList = mainRepository.selectNewsList();

        } catch(Exception e){

            log.info(e.getMessage());

        }//end try ~ catch()

        return resultList;
    }//end selectNewsList()


}//end class()
