// 'use client';

// import React, { useEffect, useState } from 'react';
// import { useParams } from 'next/navigation';
// import { User, Users, Image as LucideImage, Heart, MessageCircle } from 'lucide-react';
// import Image from 'next/image';

// const ResultsPage = () => {
//   const { username } = useParams();
//   const [data, setData] = useState(null);
//   const [loading, setLoading] = useState(true);

//   useEffect(() => {
//     const fetchData = async () => {
//       try {
//         const response = await fetch(`/api/analyze`, {
//           method: 'POST',
//           headers: {
//             'Content-Type': 'application/json',
//           },
//           body: JSON.stringify({ username }),
//         });
//         if (!response.ok) throw new Error('Failed to fetch data');
//         const result = await response.json();
//         setData(result);
//       } catch (error) {
//         console.error('Error fetching data:', error);
//       } finally {
//         setLoading(false);
//       }
//     };

//     fetchData();
//   }, [username]);

//   if (loading) return <div className="text-white text-center">Loading...</div>;
//   if (!data) return <div className="text-white text-center">No data available</div>;

//   // Ensure we have 6 posts to display
//   const topSixPosts = data.topPosts.slice(0, 6);
//   while (topSixPosts.length < 6) {
//     topSixPosts.push(null); // Add placeholders if less than 6 posts
//   }

//   return (
//     <div className="min-h-screen bg-gradient-to-br from-gray-900 to-gray-800 text-white p-8">
//       <div className="max-w-6xl mx-auto">
//         <h1 className="text-4xl font-bold text-center mb-8">Instagram Profil Analizi Sonuçları</h1>
        
//         <div className="bg-gray-800 rounded-lg p-6 shadow-lg mb-8">
//           {/* Profile info remains the same */}
//         </div>

//         <h3 className="text-2xl font-bold mb-4">En Popüler Gönderiler</h3>
//         <div className="grid grid-cols-3 gap-4">
//           {topSixPosts.map((post, index) => (
//             <div key={index} className="bg-gray-800 border border-gray-700 rounded-lg p-4">
//               {post ? (
//                 <>
//                   <Image 
//                     src={post.imageUrl} 
//                     alt={`Gönderi ${index + 1}`} 
//                     width={400} 
//                     height={400}
//                     className="w-full h-64 object-cover mb-2 rounded"
//                   />
//                   <p className="text-sm mb-2 h-12 overflow-hidden">{post.caption}</p>
//                   <div className="flex justify-between text-sm text-gray-400">
//                     <span><Heart className="inline-block mr-1" size={16} /> {post.likesCount}</span>
//                     <span><MessageCircle className="inline-block mr-1" size={16} /> {post.commentsCount}</span>
//                   </div>
//                 </>
//               ) : (
//                 <div className="w-full h-64 bg-gray-700 rounded flex items-center justify-center">
//                   <p className="text-gray-500">No post available</p>
//                 </div>
//               )}
//             </div>
//           ))}
//         </div>
//       </div>
//     </div>
//   );
// };

// export default ResultsPage;