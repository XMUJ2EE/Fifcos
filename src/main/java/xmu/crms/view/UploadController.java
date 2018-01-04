package xmu.crms.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xmu.crms.service.UploadService;

import java.io.File;
import java.math.BigInteger;
import java.util.Base64;

@RestController
public class UploadController {

    @Autowired
    public UploadService uploadService;

    @Autowired
    private ResourceLoader resourceLoader;


    @Value("${upload.base-avatar}")
    private String avatarBaseDir;
    @Value("${upload.base-report}")
    private String reportBaseDir;

    //    private static final String BASE_DIR = "/var/mywebapp/data";
    @PostMapping("/upload/avatar")
    public Object uploadAvatar(@RequestParam MultipartFile avatar,
                               @RequestAttribute("userId") BigInteger userId) {
        String curTime = String.valueOf(System.currentTimeMillis());
        String filename = Base64.getEncoder().encodeToString(curTime.getBytes());
        File file = new File(avatarBaseDir + filename);
        try {
            avatar.transferTo(file);
            uploadService.uploadAvater(userId, filename);
            return new Object() {
                public String file = filename;
            };
        } catch (Exception e) {
            return ResponseEntity.badRequest();
        }
    }

    @PostMapping("/upload/report")
    public Object uploadReport(@RequestParam MultipartFile report,
                               @RequestAttribute("userId") BigInteger userId,
                               @RequestParam("seminarId") BigInteger seminarId) {
        String curTime = String.valueOf(System.currentTimeMillis());
        String filename = Base64.getEncoder().encodeToString(curTime.getBytes());
        File file = new File(reportBaseDir + filename);
        try {
            report.transferTo(file);
            uploadService.uploadReport( seminarId,userId, filename);
            return new Object() {
                public String file = filename;
            };
        } catch (Exception e) {
            return ResponseEntity.badRequest();
        }
    }

    @GetMapping("/avatar/{avatar}")
    public HttpEntity avatar(@PathVariable("avatar") String avatar) {
        File file = new File(avatarBaseDir + avatar);
        if (file.exists() == false) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                    .body(resourceLoader.getResource("file:" + avatarBaseDir + avatar));
        }
    }

    @GetMapping("/report/{report}")
    public HttpEntity report(@PathVariable("report") String report) {
        File file = new File(reportBaseDir + report);
        if (file.exists() == false) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
                    .body(resourceLoader.getResource("file:" + reportBaseDir+ report));
        }
    }
}
