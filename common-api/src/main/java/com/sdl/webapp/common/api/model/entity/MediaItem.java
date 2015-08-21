package com.sdl.webapp.common.api.model.entity;

import java.util.HashMap;

import com.sdl.webapp.common.api.mapping.annotations.SemanticEntity;
import com.sdl.webapp.common.api.mapping.annotations.SemanticProperty;

import static com.sdl.webapp.common.api.mapping.config.SemanticVocabulary.SCHEMA_ORG;

@SemanticEntity(entityName = "MediaObject", vocabulary = SCHEMA_ORG, prefix = "s", public_ = true)
public abstract class MediaItem extends AbstractEntity {

    @SemanticProperty("s:contentUrl")
    private String url;

    private Boolean isEmbedded;
    
    private String fileName;

    @SemanticProperty("s:contentSize")
    private int fileSize;

    private String mimeType;

    public String getUrl() {
        return url;
    }

    public void setIsEmbedded(Boolean isEmbedded)
    {
    	this.isEmbedded = isEmbedded;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
	public Boolean getIsEmbedded()
	{
		return this.isEmbedded;
	}
    
	public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    
    @Override
    public String toHtml()
    {
        return this.toHtml("100%");
    }
    
    public String getIconClass()
    {
        String fileType = FontAwesomeMimeTypeToIconClassMapping.containsKey(this.getMimeType())?FontAwesomeMimeTypeToIconClassMapping.get(this.getMimeType()):null;
        return fileType == null ? String.format("fa-file-{0}-o", fileType) : "fa-file";
    }
    
    public String getFriendlyFileSize()
    {
        String[] sizes = { "B", "KB", "MB", "GB", "TB", "PB", "EB" };
        double len = getFileSize();
        int order = 0;
        while (len >= 1024 && order + 1 < sizes.length)
        {
            order++;
            len = len / 1024;
        }

        return String.format("{0} {1}", Math.ceil(len), sizes[order]);
    }
    public abstract String toHtml(String widthFactor);

    public abstract String toHtml(String widthFactor, double aspect, String cssClass, int containerSize);

    
    private static final HashMap<String, String> FontAwesomeMimeTypeToIconClassMapping;
    static
    {
    	FontAwesomeMimeTypeToIconClassMapping = new HashMap<String, String>();
    	
    	FontAwesomeMimeTypeToIconClassMapping.put("application/ms-excel", "excel");
    	FontAwesomeMimeTypeToIconClassMapping.put("application/pdf", "pdf");
    	FontAwesomeMimeTypeToIconClassMapping.put("application/x-wav", "audio");
    	FontAwesomeMimeTypeToIconClassMapping.put("audio/x-mpeg", "audio");
    	FontAwesomeMimeTypeToIconClassMapping.put("application/msword", "word");
    	FontAwesomeMimeTypeToIconClassMapping.put("text/rtf", "word");
    	FontAwesomeMimeTypeToIconClassMapping.put("application/zip", "archive");
    	FontAwesomeMimeTypeToIconClassMapping.put("image/gif", "image");
    	FontAwesomeMimeTypeToIconClassMapping.put("image/jpeg", "image");
    	FontAwesomeMimeTypeToIconClassMapping.put("image/png", "image");
    	FontAwesomeMimeTypeToIconClassMapping.put("image/x-bmp", "image");
    	FontAwesomeMimeTypeToIconClassMapping.put("text/plain", "text");
    	FontAwesomeMimeTypeToIconClassMapping.put("text/css", "code");
    	FontAwesomeMimeTypeToIconClassMapping.put("application/x-javascript", "code");
    	FontAwesomeMimeTypeToIconClassMapping.put("application/ms-powerpoint", "powerpoint");
    	FontAwesomeMimeTypeToIconClassMapping.put("video/vnd.rn-realmedia", "video");
    	FontAwesomeMimeTypeToIconClassMapping.put("video/quicktime", "video");
    	FontAwesomeMimeTypeToIconClassMapping.put("video/mpeg", "video");
    }
}