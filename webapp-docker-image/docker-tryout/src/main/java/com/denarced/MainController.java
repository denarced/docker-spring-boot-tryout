package com.denarced;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @Value("${nodeid}")
    private String nodeIdentifier;

    @RequestMapping("/")
    public @ResponseBody String main() {
        return "well hello hello: " + deriveNodeIdentifier();
    }

    private String deriveNodeIdentifier() {
        return nodeIdentifier == null || nodeIdentifier.isEmpty()
            ? "empty ID"
            : nodeIdentifier;
    }
}
