package com.scipionyx.butterflyeffect.backend.configuration.service.imp;

import java.io.IOException;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 
 * @author Reato Mendes
 *
 */
@RestController
public class GitHubService {

	@RequestMapping(value = "/backend/configuration/save/{1}")
	public void save(@PathVariable("id") int id) throws IOException {

		GitHub github = GitHub.connect();
		GHRepository repo = github.createRepository("new-repository", "this is my new repository",
				"http://www.kohsuke.org/", true);
		repo.addCollaborators(github.getUser("abayer"), github.getUser("rtyler"));
		repo.delete();

	}

}
