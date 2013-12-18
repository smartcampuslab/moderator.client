package eu.trentorise.smartcampus.moderatoservice;

import java.util.List;

import eu.trentorise.smartcampus.moderatorservice.model.ContentToModeratorService;
import eu.trentorise.smartcampus.moderatorservice.model.KeyWord;
import eu.trentorise.smartcampus.moderatorservice.model.State;
import eu.trentorise.smartcampus.moderatoservice.exception.ModeratorServiceException;
import eu.trentorise.smartcampus.network.JsonUtils;
import eu.trentorise.smartcampus.network.RemoteConnector;
import eu.trentorise.smartcampus.network.RemoteException;

public class ModeratorService {

	private String moderatorServiceURL;

	public ModeratorService(String serverURL) {
		this.moderatorServiceURL = serverURL;
		if (!moderatorServiceURL.endsWith("/"))
			moderatorServiceURL += '/';
	}

	public List<KeyWord> getAllKeywordFilterContent(
			String token, String app) throws SecurityException,
			ModeratorServiceException {
		try {
			String json = RemoteConnector.getJSON(moderatorServiceURL,
					"rest/comment/local/" + app + "/all", token);
			return JsonUtils
					.toObjectList(json, KeyWord.class);
		} catch (RemoteException e) {
			throw new ModeratorServiceException(e);
		}
	}

	public List<ContentToModeratorService> getAllManualContent(String token,
			String app) throws SecurityException, ModeratorServiceException {
		try {
			String json = RemoteConnector.getJSON(moderatorServiceURL,
					"rest/comment/remote/" + app + "/all", token);
			return JsonUtils
					.toObjectList(json, ContentToModeratorService.class);
		} catch (RemoteException e) {
			throw new ModeratorServiceException(e);
		}
	}

	public List<ContentToModeratorService> getAllManualContentByData(
			String token, String app, long data) throws SecurityException,
			ModeratorServiceException {
		try {
			String json = RemoteConnector.getJSON(moderatorServiceURL,
					"rest/comment/data/" + data + "/" + app, token);
			return JsonUtils
					.toObjectList(json, ContentToModeratorService.class);
		} catch (RemoteException e) {
			throw new ModeratorServiceException(e);
		}
	}

	public Boolean addContentToManualFilterByApp(String token, String app,ContentToModeratorService messageToMediationService)
			throws SecurityException, ModeratorServiceException {
		try {
			String json = RemoteConnector.postJSON(moderatorServiceURL,
					"rest/comment/app/" + app + "/add",
					JsonUtils.toJSON(messageToMediationService), token);
			return Boolean.parseBoolean(json);
		} catch (RemoteException e) {
			throw new ModeratorServiceException(e);
		}
	}

	public Boolean addContentNoteByApp(String token, String app,
			String idcontent, String note) throws SecurityException,
			ModeratorServiceException {
		try {
			String json = RemoteConnector.postJSON(moderatorServiceURL,
					"rest/comment/" + idcontent + "/app/" + app + "/note/add",
					JsonUtils.toJSON(note), token);
			return Boolean.parseBoolean(json);
		} catch (RemoteException e) {
			throw new ModeratorServiceException(e);
		}
	}

	public Boolean changeStatoManualFilterByApp(String token, String app,
			String idcontent, State stato) throws SecurityException,
			ModeratorServiceException {
		try {
			String json = RemoteConnector
					.putJSON(moderatorServiceURL, "rest/comment/" + idcontent
							+ "/app/" + app + "/mediationapproved/change/"
							+ stato, null, token);
			return Boolean.parseBoolean(json);
		} catch (RemoteException e) {
			throw new ModeratorServiceException(e);
		}
	}
	
	public List<ContentToModeratorService> getContentByObjectId(
			String token, String app, String objectId) throws SecurityException,
			ModeratorServiceException {
		try {
			String json = RemoteConnector.getJSON(moderatorServiceURL,
					"rest/content/id/" + objectId + "/" + app, token);
			return JsonUtils
					.toObjectList(json, ContentToModeratorService.class);
		} catch (RemoteException e) {
			throw new ModeratorServiceException(e);
		}
	}
	
	public List<ContentToModeratorService> getContentByDateWindow(
			String token, String app, long fromData,long toData) throws SecurityException,
			ModeratorServiceException {
		try {
			String json = RemoteConnector.getJSON(moderatorServiceURL,
					"rest/content/from/" + fromData + "/to/"+toData +"/"+ app, token);
			return JsonUtils
					.toObjectList(json, ContentToModeratorService.class);
		} catch (RemoteException e) {
			throw new ModeratorServiceException(e);
		}
	}
	
	public void deleteByObjectId(
			String token, String app, String idEntity) throws SecurityException,
			ModeratorServiceException {
		try {
			RemoteConnector.deleteJSON(moderatorServiceURL,
					"rest/content/id/" + idEntity + "/" + app, token);
		
		} catch (RemoteException e) {
			throw new ModeratorServiceException(e);
		}
	}
	

}
