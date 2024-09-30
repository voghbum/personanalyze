import React, { useState, useEffect } from 'react';

const AnalysisBox = ({ title, content, isExpanded, toggleExpand, isLoading }) => (
  <div className="bg-white bg-opacity-10 rounded-lg p-4 mb-4">
    <h3 className="text-xl font-bold mb-2">{title}</h3>
    {isLoading ? (
      <div className="flex items-center justify-center h-20">
        <div className="animate-spin rounded-full h-8 w-8 border-t-2 border-b-2 border-blue-500"></div>
      </div>
    ) : (
      <>
        <p className={`${isExpanded ? '' : 'line-clamp-3'}`}>{content}</p>
        <button
          className="mt-2 text-blue-300 hover:text-blue-100"
          onClick={toggleExpand}
        >
          {isExpanded ? 'Show Less' : 'Show More'}
        </button>
      </>
    )}
  </div>
);

const DetailedAnalysisModal = ({ isOpen, onClose, username }) => {
  const [analysisData, setAnalysisData] = useState({
    changeMillionaire: '',
    similarCeleb: '',
    strengthWeaknesses: '',
  });
  const [expandedBoxes, setExpandedBoxes] = useState({
    changeMillionaire: false,
    similarCeleb: false,
    strengthWeaknesses: false,
  });
  const [loading, setLoading] = useState({
    changeMillionaire: true,
    similarCeleb: true,
    strengthWeaknesses: true,
  });

  useEffect(() => {
    if (isOpen) {
      fetchAnalysisData();
    }
  }, [isOpen, username]);

  const fetchAnalysisData = async () => {
    setLoading({
      changeMillionaire: true,
      similarCeleb: true,
      strengthWeaknesses: true,
    });
    try {
      const [changeMillionaire, similarCeleb, strengthWeaknesses] = await Promise.all([
        fetch('/api/ai/change_millionaire', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ username }),
        }).then(res => res.json()),
        fetch('/api/ai/similar_celeb', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ username }),
        }).then(res => res.json()),
        fetch('/api/ai/strength_and_weaknesses', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ username }),
        }).then(res => res.json()),
      ]);
      setAnalysisData({
        changeMillionaire: changeMillionaire.ai_result,
        similarCeleb: similarCeleb.ai_result,
        strengthWeaknesses: strengthWeaknesses.ai_result,
      });
    } catch (error) {
      console.error('Error fetching analysis data:', error);
    } finally {
      setLoading({
        changeMillionaire: false,
        similarCeleb: false,
        strengthWeaknesses: false,
      });
    }
  };

  const toggleExpand = (boxKey) => {
    setExpandedBoxes(prev => ({ ...prev, [boxKey]: !prev[boxKey] }));
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-gradient-to-br from-indigo-900 via-purple-900 to-pink-700 p-8 rounded-lg max-w-2xl w-full max-h-[90vh] overflow-y-auto">
        <h2 className="text-2xl font-bold mb-4">Detailed Analysis</h2>
        <AnalysisBox
          title="Change to Millionaire"
          content={analysisData.changeMillionaire}
          isExpanded={expandedBoxes.changeMillionaire}
          toggleExpand={() => toggleExpand('changeMillionaire')}
          isLoading={loading.changeMillionaire}
        />
        <AnalysisBox
          title="Similar Celebrity"
          content={analysisData.similarCeleb}
          isExpanded={expandedBoxes.similarCeleb}
          toggleExpand={() => toggleExpand('similarCeleb')}
          isLoading={loading.similarCeleb}
        />
        <AnalysisBox
          title="Strengths and Weaknesses"
          content={analysisData.strengthWeaknesses}
          isExpanded={expandedBoxes.strengthWeaknesses}
          toggleExpand={() => toggleExpand('strengthWeaknesses')}
          isLoading={loading.strengthWeaknesses}
        />
        <button
          className="mt-4 bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded"
          onClick={onClose}
        >
          Close
        </button>
      </div>
    </div>
  );
};

export default DetailedAnalysisModal;