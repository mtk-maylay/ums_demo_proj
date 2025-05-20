package may.com.mm.component.http;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.Getter;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Getter
public class CachedBodyHttpServletRequest extends HttpServletRequestWrapper {

    private byte[] cachedBody;

    public CachedBodyHttpServletRequest(HttpServletRequest request) throws IOException {

        super(request);

        InputStream requestInputStream = request.getInputStream();

        if (requestInputStream == null) {

            return;

        }

        this.cachedBody = StreamUtils.copyToByteArray(requestInputStream);
    }



    @Override
    public ServletInputStream getInputStream() throws IOException {

        return new CachedBodyServletInputStream(this.cachedBody);

    }

    @Override
    public BufferedReader getReader() throws IOException {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.cachedBody);

        return new BufferedReader(new InputStreamReader(byteArrayInputStream));

    }

}