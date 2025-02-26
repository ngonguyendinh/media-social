import { Avatar, Grid, IconButton } from '@mui/material'
import WestIcon from '@mui/icons-material/West';
import AddIcCallIcon from '@mui/icons-material/AddIcCall';
import VideoCallIcon from '@mui/icons-material/VideoCall';
import AddPhotoAlternateIcon from '@mui/icons-material/AddPhotoAlternate';
import React, { useEffect, useState } from 'react'
import { AddPhotoAlternate } from '@mui/icons-material';
import SearchUser from '../../components/SearchUser/SearchUser';
import ChatBubbleOutlineIcon from '@mui/icons-material/ChatBubbleOutline';
import UserChatCard from './UserChatCard';
import ChatMessage from './ChatMessage';
import { useDispatch, useSelector } from 'react-redux';
import { createMessageAction, getAllChats } from '../../Redux/Message/message.action';
import { uploadToCloudinary } from '../../utils/uploadToCloudinary';


const Message = () => {
  const { message, auth } = useSelector((store) => store);
  const dispatch = useDispatch();
  const [currentChat, setCurrentChat] = useState();
  const [messages, setMessages] = useState([]);
  const [selectedImage, setSelectImage] = useState();
  const [loading, setLoading] = useState(false);
  useEffect(() => {
    dispatch(getAllChats());
  }, []);
  const handleCreateMessage = (value) => {
    const message = {
      chatId: currentChat.id,
      content: value,
      image: setSelectImage,
      
    }
    console.log("message state:", message);
console.log("messages state:", messages);
console.log("currentChat:", currentChat);


    dispatch(createMessageAction(message))
  }
  const handleSelectImage = async (e) => {
    setLoading(true);
    const imgUrl = await uploadToCloudinary(e.target.files[0], "image");
    setSelectImage(imgUrl);
    setLoading(false);
  }
  useEffect(() => {
    setMessages([...messages, message.message])
  }, [message.message])
  
  
  return (
    <div>
      <Grid container className='h-screen overflow-y-hidden'>
        <Grid className='px-5' item xs={3}>
          <div className='h-full flex justify-between space-x-2'>
            <div className='w-full'>
              <div className='flex space-x-4 items-center py-5'>
                <WestIcon />
                <h1 className='text-xl font-bold'>Home</h1>
              </div>
              <div className=' h-[83vh]'>
                <div className=''>
                  <SearchUser />
                </div>
                <div className='h-full space-y-4 mt-5 overflow-y-scroll hideScrollbar'>
                  {
                    message.chat.map((item) => {
                      return <div onClick={() => {
                        setCurrentChat(item)
                        setMessages(item.messages)
                      }}>
                        <UserChatCard chat={item} />
                      </div>
                    })
                  }

                </div>
              </div>
            </div>
          </div>
        </Grid>
        <Grid className='h-full ' item xs={9}>
          {currentChat ? <div>
            <div className='flex justify-between  items-center border-l p-5'>
              <div className='flex  items-center space-x-3 '>

                <Avatar sx={{ width: "3.5rem", height: "3.5rem", fontSize: "1.5rem" }} src={auth.user.id === currentChat.users[0].id ? currentChat.users[1].avatar
                  : currentChat.users[0].avatar} />
                <p>{auth.user.id === currentChat.users[0].id ? currentChat.users[1].firstName + " " + currentChat.users[1].lastName
                  : currentChat.users[0].firstName + " " + currentChat.users[0].lastName}</p>
              </div>
              <div className='flex space-x-3'>
                <IconButton>
                  <AddIcCallIcon></AddIcCallIcon>

                </IconButton>
                <IconButton>
                  <VideoCallIcon></VideoCallIcon>
                </IconButton>

              </div>
            </div>
            <div className='hidenScrollbar overflow-y-scroll h-[82vh] px-2 space-y-5 py-5'>
              {messages.map((item) => <ChatMessage item={item} />)}
            </div>
            <div className='sticky  bottom-0 border-l'>
              <div className='py-5 flex items-center justify-center space-x-5'>

                <input className='bg-transparent border border-[#3b4054] rounded-full w-[90%] py-3 px-5'
                  placeholder='Type message....'
                  onKeyPress={(e) => {
                    if (e.key === "Enter" && e.target.value) {
                      handleCreateMessage(e.target.value)
                    }
                  }}
                  type="text" />
                <div>
                  <input type="file" accept='image/*' onChange={handleSelectImage} className='hidden' id='image-input' />
                  <label htmlFor="image-input">
                    <AddPhotoAlternateIcon />
                  </label>
                </div>
              </div>

            </div>
          </div> : <div className='h-full space-y-5 flex flex-col justify-center items-center'>
            <ChatBubbleOutlineIcon sx={{ fontSize: "15rem" }} />
            <p className='text-xl font-semibold'>No chat selected</p>

          </div>}

        </Grid>
      </Grid>
    </div>
  )
}

export default Message