package com.gladigator.Controllers.Utils;

import java.security.InvalidParameterException;
import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.thymeleaf.util.MapUtils;

import com.gladigator.Entities.BodyType;
import com.gladigator.Entities.CalculateBMIRequest;
import com.gladigator.Entities.CalculateBMIResponse;
import com.gladigator.Entities.CalculateBMRRequest;
import com.gladigator.Entities.CalculateBMRResponse;
import com.gladigator.Entities.FrequencyOfActivity;
import com.gladigator.Entities.Gender;
import com.gladigator.Entities.Sex;
import com.gladigator.Entities.Translation;
import com.gladigator.Entities.Translationable;
import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;
import com.gladigator.Entities.Enums.BodyTypeTypes;
import com.gladigator.Entities.Enums.FrequencyOfActivityTypes;
import com.gladigator.Entities.Enums.SexTypes;
import com.gladigator.Services.BmiBmrServiceClient;
import com.gladigator.Services.UserDetailsService;
import com.gladigator.Services.UserService;

@Component
public class ProfileUtils {

	private static final Logger LOG = LoggerFactory.getLogger(ProfileUtils.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private BmiBmrServiceClient bmiBmrService;

	public UserDetails obtainUserDetails(Principal principal) {
		String authenticatedUserUsername = principal.getName();
		User user = userService.getUserByUsername(authenticatedUserUsername);

		UserDetails userDetailsFromUserObtainedForUpdate = user.getUserDetails();
		LOG.debug("UserDetails from user = {}", userDetailsFromUserObtainedForUpdate);
		// Jesli User ma juz jakies UserDetails
		if (userDetailsFromUserObtainedForUpdate != null) {
			user.getUserDetails().setUser(user);
			return user.getUserDetails();
		}

		// Jesli User pierwszy raz ustawia UserDetails
		UserDetails emptyUserDetails = new UserDetails();
		emptyUserDetails.setBodyType(new BodyType() {
			{
				setBodyTypeId(BodyTypeTypes.NONE.getIndex());
			}
		});
		emptyUserDetails.setFrequencyOfActivity(new FrequencyOfActivity() {
			{
				setFrequencyOfActivityId(FrequencyOfActivityTypes.NONE.getIndex());
			}
		});
		emptyUserDetails.setSex(new Sex() {
			{
				setSexId(SexTypes.NONE.getIndex());
			}
		});
		emptyUserDetails.setUser(user);
		return emptyUserDetails;
	}

	public void addListsOfAttributesToModel(Model model, Locale locale) {
		Map<String, List<?>> listOfSelectives = userDetailsService.getSelectiveDetailsAsMap(locale);
		model.addAllAttributes(listOfSelectives);
	}

	public CalculateBMIResponse prepareBMIResponse(UserDetails userDetails) {
		Integer height = userDetails.getHeight();
		Integer weight = userDetails.getWeight();
		CalculateBMIRequest bmiRequest = new CalculateBMIRequest();
		CalculateBMIResponse bmiResponse = null;
		
		if(ObjectUtils.allNotNull(height, weight)) {
			bmiRequest.setHeight(height);
			bmiRequest.setWeight(weight);
			
			bmiResponse = bmiBmrService.callBmiService(bmiRequest);
		} else {
			throw new InvalidParameterException("Some user details wasn't filled");
		}
		
		return bmiResponse;
	}

	public CalculateBMRResponse prepareBMRResponse(UserDetails userDetails) {
		Integer userAge = userDetails.getAge();
		FrequencyOfActivityTypes userFOA = FrequencyOfActivityTypes
				.valueOf(userDetails.getFrequencyOfActivity().getFrequencyOfActivityId());
		SexTypes userSex = SexTypes.valueOf(userDetails.getSex().getSexId());
		Integer userHeight = userDetails.getHeight();
		Integer userWeight = userDetails.getWeight();
		CalculateBMRRequest bmrRequest = new CalculateBMRRequest();
		CalculateBMRResponse bmrResponse = null;

		if (ObjectUtils.allNotNull(userAge, userFOA, userSex, userHeight, userWeight)
				&& (ObjectUtils.notEqual(userSex, SexTypes.NONE))) {
			bmrRequest.setHeight(userHeight);
			bmrRequest.setWeight(userWeight);
			bmrRequest.setActivityFrequency(userFOA.ordinal());
			bmrRequest.setAge(userAge);
			Gender userGender = evaluateGender(userSex);
			bmrRequest.setGender(userGender);
			bmrResponse = bmiBmrService.callBmrService(bmrRequest);
		} else
			throw new InvalidParameterException("Some user details wasn't filled");

		return bmrResponse;

	}
	
	@SuppressWarnings("unchecked")
	public void setTranslations(Model model, Locale locale) {
		List<Translationable<Translation>> frequencyOfActivity = (List<Translationable<Translation>>) model.asMap().get("frequenciesListOfSelectives");
		userDetailsService.setTranslationAccordingToLocale(frequencyOfActivity, locale);
		
		List<Translationable<Translation>> sex = (List<Translationable<Translation>>) model.asMap().get("sexListOfSelectives");
		userDetailsService.setTranslationAccordingToLocale(sex, locale);
		
		List<Translationable<Translation>> bodyType = (List<Translationable<Translation>>) model.asMap().get("bodyTypeListOfSelectives");
		userDetailsService.setTranslationAccordingToLocale(bodyType, locale);
	}
	
	public boolean containsAllSessionAttributes(Model model) {
		return MapUtils.containsAllKeys(model.asMap(), new String[]{"userDetails", "bodyTypeListOfSelectives", "sexListOfSelectives", "frequenciesListOfSelectives"});
	}

	private Gender evaluateGender(SexTypes userSex) {
		if (SexTypes.MALE.equals(userSex))
			return Gender.M;

		return Gender.F;
	}

}
