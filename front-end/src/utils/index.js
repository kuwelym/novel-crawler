export const scrollToTopSmooth = () => {
  window.scrollTo({
    top: 0,
    left: 0,
    behavior: "smooth",
  });
};

export const removeVietnameseTones = (str) => {
  var accentsMap = new Map([
    ["a", "áàạảãâấầậẩẫăắằặẳẵ"],
    ["e", "éèẹẻẽêếềệểễ"],
    ["i", "íìịỉĩ"],
    ["o", "óòọỏõôốồộổỗơớờợởỡ"],
    ["u", "úùụủũưứừựửữ"],
    ["y", "ýỳỵỷỹ"],
    ["d", "đ"],
  ]);

  for (let [char, accents] of accentsMap) {
    for (let accent of accents) {
      str = str.replaceAll(accent, char);
      str = str.replaceAll(accent.toUpperCase(), char.toUpperCase());
    }
  }

  return str;
};

export const convertToSlug = (str) => {
  // Remove Vietnamese tones
  str = removeVietnameseTones(str);

  // Convert to lower case
  str = str.toLowerCase();

  // Replace spaces with hyphens
  str = str.replace(/\s+/g, "-");

  // Remove all non-alphanumeric characters except for hyphens
  str = str.replace(/[^a-z0-9-]/g, "");

  return str;
};
