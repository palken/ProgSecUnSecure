package no.ntnu.tdt4237;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.DiskFileUpload;

public class MultiPartParser {

	public static Map<String, String[]> parseRequest(Map<String, String> keys, HttpServletRequest request,
			ServletContext context, String encoding)
	{
		if (FileUpload.isMultipartContent(request))
		{
			try
			{
				DiskFileUpload upload = new DiskFileUpload();
				Map multipartFiles = new HashMap();
				Map<String, String[]> multipartParameters = new HashMap<String, String[]>();
				List fileItems = upload.parseRequest(request);
				for (Iterator it = fileItems.iterator(); it.hasNext();) {
                    FileItem fileItem = (FileItem) it.next();
                    if (fileItem.isFormField()) {
                        String value = null;
                        if (encoding != null) {
                            try {
                                value = fileItem.getString(encoding);
                            } catch (UnsupportedEncodingException ex) {

                                value = fileItem.getString();
                            }
                        } else {
                            value = fileItem.getString();
                        }
                        String[] curParam = (String[]) multipartParameters
                                .get(fileItem.getFieldName());
                        if (curParam == null) {
                            // simple form field
                            multipartParameters.put(fileItem.getFieldName(),
                                    new String[] { value });
                        } else {
                            // array of simple form fields
                        	String[] newParam = new String[curParam.length + 1];
							System.arraycopy(curParam, 0, newParam, 0,
									curParam.length);
							newParam[curParam.length] = value;
                            multipartParameters.put(fileItem.getFieldName(),
                                    newParam);
                        }
                    } else {
                        // multipart file field
                    	String fileName = fileItem.getName();
                    	File cfile = new File(fileName);
                    	
                    	if (fileItem.getContentType() != null && fileItem.getContentType() != "")
                    	{
	                    	File tosave = new File(context.getRealPath( "/pictures" ), cfile.getName());
	                    	fileItem.write(tosave);
	                    	multipartParameters.put("FileName", new String[] {fileName});
                    	}
                    }
                }
                return multipartParameters;
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
		}
		return null;
	}
}
