import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yang.pojo.FaceAnalyze;
import com.yang.pojo.Faces;
import com.yang.utils.ApiUtil;

import java.util.List;

public class test {
    public static void main(String[] args) {
        String face_token = "f2094a12cff31dc028b1366af0fec497";
        String result = ApiUtil.result(face_token);
        System.out.println(result);
        FaceAnalyze faceAnalyze = JSON.parseObject(result, FaceAnalyze.class);
        List<Faces> faces = faceAnalyze.getFaces();
        System.out.println(faces.get(0).getAttributes().get(0).getAge());
    }
}
