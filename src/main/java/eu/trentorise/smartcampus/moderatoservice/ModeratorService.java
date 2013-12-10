package eu.trentorise.smartcampus.moderatoservice;

import java.util.List;

import eu.trentorise.smartcampus.moderatorservice.model.MessageToMediationService;
import eu.trentorise.smartcampus.moderatorservice.model.Stato;
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

	public List<MessageToMediationService> getAllKeywordFilterContent(
			String token, String app) throws SecurityException,
			ModeratorServiceException {
		try {
			String json = RemoteConnector.getJSON(moderatorServiceURL,
					"/rest/comment/local/" + app + "/all", token);
			return JsonUtils
					.toObjectList(json, MessageToMediationService.class);
		} catch (RemoteException e) {
			throw new ModeratorServiceException(e);
		}
	}

	public List<MessageToMediationService> getAllManualContent(String token,
			String app) throws SecurityException, ModeratorServiceException {
		try {
			String json = RemoteConnector.getJSON(moderatorServiceURL,
					"/rest/comment/remote/" + app + "/all", token);
			return JsonUtils
					.toObjectList(json, MessageToMediationService.class);
		} catch (RemoteException e) {
			throw new ModeratorServiceException(e);
		}
	}

	public List<MessageToMediationService> getAllManualContentByData(
			String token, String app, long data) throws SecurityException,
			ModeratorServiceException {
		try {
			String json = RemoteConnector.getJSON(moderatorServiceURL,
					"/rest/comment/data/" + data + "/" + app, token);
			return JsonUtils
					.toObjectList(json, MessageToMediationService.class);
		} catch (RemoteException e) {
			throw new ModeratorServiceException(e);
		}
	}

	public String addContentToManualFilterByApp(String token, String app,MessageToMediationService messageToMediationService)
			throws SecurityException, ModeratorServiceException {
		try {
			String json = RemoteConnector.postJSON(moderatorServiceURL,
					"/rest/comment/app/" + app + "/add",
					JsonUtils.toJSON(messageToMediationService), token);
			return json;
		} catch (RemoteException e) {
			throw new ModeratorServiceException(e);
		}
	}

	public String addContentNoteByApp(String token, String app,
			String idcontent, String note) throws SecurityException,
			ModeratorServiceException {
		try {
			String json = RemoteConnector.postJSON(moderatorServiceURL,
					"/rest/comment/" + idcontent + "/app/" + app + "/note/add",
					JsonUtils.toJSON(note), token);
			return json;
		} catch (RemoteException e) {
			throw new ModeratorServiceException(e);
		}
	}

	public String changeStatoManualFilterByApp(String token, String app,
			String idcontent, Stato stato) throws SecurityException,
			ModeratorServiceException {
		try {
			String json = RemoteConnector
					.putJSON(moderatorServiceURL, "/rest/comment/" + idcontent
							+ "/app/" + app + "/mediationapproved/change/"
							+ stato, null, token);
			return json;
		} catch (RemoteException e) {
			throw new ModeratorServiceException(e);
		}
	}

}
