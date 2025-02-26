import { Avatar, Box, Button, Card, Tab, Tabs } from "@mui/material";
import React, { useState } from "react";
import { useParams } from "react-router-dom";
import PostCard from "../../components/Post/PostCard";
import UserReelCard from "../../components/Reels/UserReelCard";
import { useSelector } from "react-redux";
import ProfileModals from "./ProfileModals";


const tabs = [
  { value: "post", name: "Post" },
  { value: "reels", name: "Reels" },
  { value: "saved", name: "Saved" },
  { value: "repost", name: "repost" },
];
const formatId = (str) =>
  str
    .normalize("NFD") // Chuẩn hóa Unicode
    .replace(/[\u0300-\u036f]/g, "") // Xóa dấu tiếng Việt
    .replace(/[^\w\s]/g, "") // Xóa dấu câu
    .replace(/\s+/g, "") // Xóa khoảng trắng
    .toLowerCase(); // Chuyển thành chữ thường


const posts = [1, 1, 1, 1, 1];
const rells = [1, 1, 1, 1, 1, 1];
const savePost = [1, 1, 1];
const Profile = () => {
  const { id } = useParams();
  const [open, setOpen] = useState(false);

  const handleOpenProfileModals = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const [value, setValue] = React.useState("post");
  const { auth } = useSelector((store) => store);
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  const { post } = useSelector(store => store);

  return (
    <Card className="my-10 w-[70%] mr-16">
      <div className="rounded-md">
        <div className="h-[15rem]">
          <img
            className="w-full h-full rounded-t-md"
            src={auth.user?.backGround}
            alt=""
          />
        </div>
        <div className="flex px-5 justify-between items-start mt-5 h-[5rem]">
          <Avatar
            className="transform -translate-y-24  "
            sx={{ width: "10rem", height: "10rem" }}
            src={auth.user?.avatar}
          />
          {true ? (
            <Button onClick={handleOpenProfileModals} sx={{ borderRadius: "20px" }} variant="outlined">
              Edit Profile
            </Button>
          ) : (
            <Button sx={{ borderRadius: "20px" }} variant="outlined">
              Follow
            </Button>
          )}
        </div>
        <div className="p-5">
          <div>
            <h1 className="py-1 font-bold text-xl">{auth.user?.firstName + " " + auth.user?.lastName}</h1>
            <p>@{formatId(auth.user?.firstName) + "_" + formatId(auth.user?.lastName)}</p>
          </div>
          <div className="flex gap-5 items-center py-3">
            <span>41 post</span>
            <span>35 followrs</span>
            <span>5 followings</span>
          </div>
          <div>Lizards are a widespread group of squamate reptiles</div>
        </div>
        <section>
          <Box sx={{ width: "100%", borderBottom: 1, borderColor: "divider" }}>
            <Tabs
              value={value}
              onChange={handleChange}
              aria-label="wrapped label tabs example"
            >
              {tabs.map((item) => (
                <Tab value={item.value} label={item.name} wrapped />
              ))}
            </Tabs>
          </Box>

           <div className="flex justify-center">
            {value === "post" ? (
              <div className="space-y-5 w-[70%] my-10">
                {post.posts.map((item) => (
                  <div className="border border-slate-100 rounded-md">
                    <PostCard item={item} />
                  </div>
                ))}
              </div>
            ) :
            value === "reels" ? ( 
              <div className="flex justify-center flex-wrap gap-2 my-10">
                {rells.map(() => (
                  <UserReelCard />
                ))}
              </div>
            ) : value === "saved" ? (
              <div className="space-y-5 w-[70%] my-10">
                {savePost.map((item) => (
                  <div className="border border-slate-100 rounded-md">
                    <PostCard item={item}/>
                  </div>
                ))}
              </div>
            ) : (
              <div>Repost</div>
            )}
          </div>
        </section>
      </div>
      <section>
        <ProfileModals open={open} handleClose={handleClose} user={auth.user} />
      </section>
    </Card>
  );
};

export default Profile;
