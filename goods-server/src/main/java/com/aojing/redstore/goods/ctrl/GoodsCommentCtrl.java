package com.aojing.redstore.goods.ctrl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dto.CommentDto;
import com.aojing.redstore.goods.pojo.GoodsComentInfo;
import com.aojing.redstore.goods.service.GoodsComentInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gexiao
 * @date 2018/12/10 10:10
 */
@RestController
public class GoodsCommentCtrl {

    @Autowired
    private GoodsComentInfoService comentInfoService;


    @PostMapping("/comment")
    public Result comment(CommentDto commentDto) {
        GoodsComentInfo comentInfo = new GoodsComentInfo();
        BeanUtils.copyProperties(commentDto, comentInfo);
        return comentInfoService.comment(comentInfo);
    }
    @PostMapping("/discomment")
    public Result discomment(Integer id,String commenterId) {
        return comentInfoService.discomment(id,commenterId);
    }
}
