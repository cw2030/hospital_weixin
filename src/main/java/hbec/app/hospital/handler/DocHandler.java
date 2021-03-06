package hbec.app.hospital.handler;

import hbec.app.hospital.repository.HospitalRepository;
import hbec.platform.commons.annotations.HbecUriHandler;
import hbec.platform.commons.annotations.Inject;
import hbec.platform.commons.container.IJsonResponse;
import hbec.platform.commons.container.IRequest;
import hbec.platform.commons.container.ReadOnlyHttpParams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

public class DocHandler {
	
	@Inject
	private HospitalRepository repository;
	
	private static Logger logger = LoggerFactory.getLogger(DocHandler.class);

	/**
	 * 显示所有常见的问题
	 * @param req
	 * @param resp
	 */
	@HbecUriHandler(uris="/index/question")
	public void questionList(IRequest req, IJsonResponse resp){
		
	}
	
	/**
	 * 显示所有的医生分组
	 * @param req
	 * @param resp
	 */
	@HbecUriHandler(uris="/index/group")
	public void docGroup(IRequest req, IJsonResponse resp){
		
	}
	
	/**
	 * 要所常见问题查询对应的医生列表
	 * @param req
	 * @param resp
	 */
	@HbecUriHandler(uris="/doctorlist")
	public void queryDocForQuestiion(IRequest req, IJsonResponse resp){
		ReadOnlyHttpParams params = req.getParams();
		Arrays.asList(params.getNames()).forEach(key -> {
			logger.info("[doctorlist]key:{}, value:{}",key, params.getValue(key));
		});
		String questionTypeName = req.getParams().getValue("questionTypeName");
		String groupTypeName = req.getParams().getValue("groupTypeName");
		if(!Strings.isNullOrEmpty(questionTypeName)){
			logger.info("[GetDocsForQuestionTypeName]{}", questionTypeName);
			resp.setData(repository.selectDoctorsForQuestion(questionTypeName));
		}else{
			if(!Strings.isNullOrEmpty(groupTypeName)){
				logger.info("[GetDocsForGroupName]{}", groupTypeName);
				resp.setData(repository.selectDoctorsForGroup(groupTypeName));
			}else{
				Map<String,String> result = new HashMap<>();
				result.put("result", "0");
				resp.setData(result);
			}
		}
	}
	
	/**
	 * 要所分组信息查询对应的医生
	 * @param req
	 * @param resp
	 */
	@HbecUriHandler(uris="/index/g/d")
	public void queryDocForGroup(IRequest req, IJsonResponse resp){
		
	}
}
