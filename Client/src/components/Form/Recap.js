// eslint-disable-next-line import/no-named-as-default
import ReCAPTCHA from 'react-google-recaptcha';

function Recap() {
  const onChange = (value) => {
    console.log('Captcha value:', value);
  };

  return (
    <ReCAPTCHA
      sitekey="6LeAKskiAAAAAEakbkKCcu_A-Lm5p0okXMuBBvPJ"
      onChange={onChange}
    />
  );
}
export default Recap;
