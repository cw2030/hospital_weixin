package hbec.app.hospital.handler;

import hbec.app.hospital.domain.IndexQuerCondition;
import hbec.app.hospital.repository.HospitalRepository;
import hbec.platform.commons.annotations.Form;
import hbec.platform.commons.annotations.HbecUriHandler;
import hbec.platform.commons.annotations.Inject;
import hbec.platform.commons.container.IJsonResponse;
import hbec.platform.commons.container.IRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexHandler {

	@Inject
	private HospitalRepository indexRepository;
	
	private static Logger logger = LoggerFactory.getLogger(IndexHandler.class);
	
	@HbecUriHandler(uris={"/"})
	public void index0(IRequest req, IJsonResponse resp,@Form IndexQuerCondition condition){
		//MOCK
		resp.setData("0");
	}
	
	@HbecUriHandler(uris={"/index.htm","/index"})
	public void index(IRequest req, IJsonResponse resp,@Form IndexQuerCondition condition){
		//MOCK
		resp.setData(indexRepository.index(condition));
	}
	
	@HbecUriHandler(uris="/ask/self")
	public void querySelfQuestion(IRequest req, IJsonResponse resp,@Form IndexQuerCondition condition){
		String openId = req.getParams().getValue("openId");
		logger.info("openId:{},conditon:{}", openId, condition.getOpenId());
		condition.setOpenId(openId);
		resp.setData(indexRepository.indexSelf(condition));
	}
	
	
	@HbecUriHandler(uris="/i/q")
	public void questionTypeList(IJsonResponse resp){
		resp.setData(indexRepository.selectQuestionType());
	}
	
	@HbecUriHandler(uris="/i/g")
	public void docGroupList(IJsonResponse resp){
		resp.setData(indexRepository.selectDoctorGroup());
	}
	
}
