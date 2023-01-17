package com.wishnu.notetaking.initializer;



import java.io.IOException;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

public final class ResponseLoggingFilter extends OncePerRequestFilter {

    private int maxPayloadLength = 50;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        ContentCachingResponseWrapper wrapper = new ContentCachingResponseWrapper(response);
        filterChain.doFilter(request, wrapper);
        if (logger.isDebugEnabled()) {
            logger.debug(createMessage(request, wrapper));
        }
        wrapper.copyBodyToResponse();
    }

    protected String createMessage(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder msg = new StringBuilder();
        msg.append("uri=").append(request.getRequestURI());
        String client = request.getRemoteAddr();
        if (StringUtils.hasLength(client)) {
            msg.append(";client=").append(client);
        }

        String user = request.getRemoteUser();
        if (user != null) {
            msg.append(";user=").append(user);
        }

        String responsePayload = getResponsePayload(response);
        if (responsePayload != null) {
            msg.append(";response=").append(responsePayload);
        }

        return msg.toString();
    }

    protected String getResponsePayload(HttpServletResponse response) {

        String payload = null;
        final ContentCachingResponseWrapper wrapper =
                WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        final byte[] buf = wrapper.getContentAsByteArray();
        if (buf.length > 0) {
            int length = Math.min(buf.length, getMaxPayloadLength());
            try {
                payload = new String(buf, 0, length, wrapper.getCharacterEncoding());
            } catch (IOException ex) {
                return null;
            }
        }
        return payload;
    }

    protected int getMaxPayloadLength() {
        return this.maxPayloadLength;
    }

    public void setMaxPayloadLength(int maxPayloadLength) {
        Assert.isTrue(maxPayloadLength >= 0, "'maxPayloadLength' should be larger than or equal to 0");
        this.maxPayloadLength = maxPayloadLength;
    }
}
