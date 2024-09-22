package com.voghbum.application.data.repository;

import com.voghbum.application.data.entity.ProfileInformation;
import com.voghbum.instaprovider.data.UserPosts;
import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserStories;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProfileInformationRepository extends CrudRepository<ProfileInformation, Long> {
    ProfileInformation findByNickName(String nickName);
    boolean existsByNickName(String nickName);
    void createOrUpdate(ProfileInformation profileInformation);
    void updateUserProfileByNickName(String nickName, UserProfile profileInformation);
    void updateUserPostsByNickName(String nickName, List<UserPosts.Item> profileInformation);
    void updateUserStoriesByNickName(String nickName, UserStories profileInformation);
}