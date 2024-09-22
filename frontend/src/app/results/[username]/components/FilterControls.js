import React from 'react';

const FilterControls = ({ filterCriteria, setFilterCriteria, sortOrder, setSortOrder }) => {
  return (
    <div className="flex justify-between items-center mb-6">
      <div>
        <label htmlFor="filterCriteria" className="mr-2">Filter by:</label>
        <select
          id="filterCriteria"
          value={filterCriteria}
          onChange={(e) => setFilterCriteria(e.target.value)}
          className="bg-purple-700 text-white rounded p-2"
        >
          <option value="likes">Likes</option>
          <option value="comments">Comments</option>
        </select>
      </div>
      <div>
        <label htmlFor="sortOrder" className="mr-2">Sort order:</label>
        <select
          id="sortOrder"
          value={sortOrder}
          onChange={(e) => setSortOrder(e.target.value)}
          className="bg-purple-700 text-white rounded p-2"
        >
          <option value="desc">Descending</option>
          <option value="asc">Ascending</option>
        </select>
      </div>
    </div>
  );
};

export default FilterControls;